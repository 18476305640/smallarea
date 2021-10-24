//git ssh凭证ID
def git_auth = "6580839e-df2c-4cc1-99d2-d1ead776a99c"
//git的url地址
def git_url = "git@192.168.87.100:zhuangjie/smallarea.git"

//镜像的版本号
def tag = "latest"
//Harbor的url地址
def harbor_url = "192.168.87.102:85"
//镜像库项目名称
def harbor_project = "smallarea"
//Harbor的登录凭证ID
def harbor_auth = "de8976d1-1acc-41f8-ad57-42c1eebb4ecb"
def find_block = [
    "gateway-service":"distributed-smallarea-security",
    "uaa-service":"distributed-smallarea-security",
    "cart-service":"distributed-smallarea-service",
    "product-service":"distributed-smallarea-service",
    "store-service":"distributed-smallarea-service",
    "user-service":"distributed-smallarea-service"
]
//common's install
def commons_block = "./,./distributed-smallarea-common,distributed-smallarea-security,distributed-smallarea-service".split(',')
node {
    //获取当前选择的项目名称
    def selectedProjectNames = "${project_name}".split(",")
    //把选择的服务器信息转为数组
    def selectedServers = "${publish_server}".split(',')

   stage('拉取代码') {
      checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
   }
   stage('代码审查') {
        for (int i=0; i<selectedProjectNames.length; i++) {
            //tensquare_eureka_server@10086
            def projectInfo = selectedProjectNames[i];
            //当前遍历的项目名称
            def currentProjectName = "${projectInfo}".split("@")[0]
            //当前遍历的项目端口
            def currentProjectPort = "${projectInfo}".split("@")[1]
            def scannerHome=tool 'SonarQube-Scanner'// 在“全局工具” ~:8888/configureTools/ 查看
            withSonarQubeEnv('sonar6.7'){ //在“系统配置” ~8888/configure 查看
                def path_block = find_block[currentProjectName];
                sh """
            		cd ${path_block}/${currentProjectName}
            		${scannerHome}/bin/sonar-scanner
            	"""
            }

        }

   }
   stage('编译，安装公共子工程') {
        for (int i=0; i<commons_block.length; i++) {
            def install_path = commons_block[i]
   	        sh "mvn -f ${install_path} clean install"
   	    }
   }
   stage('编译，打包微服务工程，上传镜像') {
        for (int i=0; i<selectedProjectNames.length; i++) {
            //tensquare_eureka_server@10086
            def projectInfo = selectedProjectNames[i];
            //当前遍历的项目名称
            def currentProjectName = "${projectInfo}".split("@")[0]
            //当前遍历的项目端口
            def currentProjectPort = "${projectInfo}".split("@")[1]
            sh "echo '开始制作镜像'"
            def parents = find_block[currentProjectName]
            sh "mvn -f ${parents}/${currentProjectName}  clean package dockerfile:build"
            sh "echo '镜像制作好了'"
            //定义镜像名称
            def _currentProjectName = currentProjectName.split('-')[0]
            def _imageName = "${_currentProjectName}:${tag}"
            def imageName = "${currentProjectName}:${tag}"
            //对镜像打上标签
            echo "正在打标签,${imageName}"
            sh "docker tag ${_imageName} ${harbor_url}/${harbor_project}/${imageName}"
            echo "打标签完成！"
            //把镜像推送到Harbor
            withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
                    //登录到Harbor
                    sh "docker login -u ${username} -p ${password} ${harbor_url}"
                    //镜像上传
                    sh "docker push ${harbor_url}/${harbor_project}/${imageName}"
                    sh "echo 镜像上传成功"
            }
           //=====以下为远程调用进行项目部署========
           for(int j=0; j<selectedServers.size(); j++){
               //每个服务名称
               def currentServer = selectedServers[j]
               //添加微服务运行时的参数：spring.profiles.active
               def activeProfile = "--spring.profiles.active="
               if(currentServer=="master_server"){
                    activeProfile = activeProfile+"eureka-server1"
               }else if(currentServer=="slave_server1"){
                    activeProfile = activeProfile+"eureka-server2"
               }
                sh "echo '应用部署开始'"
                sshPublisher(publishers: [
                       sshPublisherDesc(configName: "${currentServer}",
                       transfers: [
                           sshTransfer(cleanRemote: false,
                           excludes: '',
                           // 触发的命令，deploy.sh
                           execCommand: "/opt/jenkins_shell/deployCluster.sh $harbor_url $harbor_project $currentProjectName $tag $currentProjectPort $activeProfile",
                           execTimeout: 120000,
                           flatten: false,
                           makeEmptyDirs: false,
                           noDefaultExcludes: false,
                           patternSeparator: '[ , ]+',
                           remoteDirectory: '',
                           remoteDirectorySDF: false,
                           removePrefix: '',
                           sourceFiles: '')
                       ],
                       usePromotionTimestamp: false,
                       useWorkspaceInPromotion: false,
                       verbose: false)
                ])
                sh "echo '应用部署结束'"
            }
        }
   }

}