
node {

  stage("Codefetch"){
    echo "SCM module trying to fetch the source code ....."
	git branch: 'main', credentialsId: '7cc213e6-d81e-43ea-80b3-5c593487846d', url: 'https://github.com/DevopsTrainingInfoOrg/SampleWebApplication.git'
	echo "SCM done"
  }
  
  stage("build"){
  echo "Starting build process ....."
  bat 'mvn clean install'
  echo "Build process  Ended....."
  }
  
  stage("TestCase"){
    bat 'mvn test'
  }
  
  stage("artifacts"){
  }
  
  stage("Deployingintoserver"){
     echo 'trying to copy the files into the server web apps folder ..... '
     bat 'cp SampleWebApplication-1.0-SNAPSHOT.war C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.5\\webapps'
	 echo 'copy success '
  }

}
