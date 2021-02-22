
node {

  stage("Codefetch"){
    echo "the file is from zzz.groovy"
    echo "SCM module trying to fetch the source code ....."
	git branch: 'main', credentialsId: '7cc213e6-d81e-43ea-80b3-5c593487846d', url: 'https://github.com/DevopsTrainingInfoOrg/SampleWebApplication.git'
	echo "SCM done"
  }
  
  stage("build"){
  echo "Starting build process  my test...."
  bat 'mvn clean install'
  echo "Build process  Ended....."
	  emailext attachLog: true, body: '''Hi Developer,

Everything looks good moving to next step

with regards
Test''', compressLog: true, replyTo: 'SGdevelopment@tcs.com', subject: '[MyProject]: Error in the uploading the files ', to: 'naveen2test@gmnail.com'
  }
  
  stage("TestCase"){
    bat 'mvn test'
  }
  
  stage("artifacts"){
  }
  
  stage("Deployingintoserver"){
	  try{
		      echo 'trying to copy the files into the server web apps folder ..... '
    			 bat 'cp SampleWebApplication-1.0-SNAPSHOT.war C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.5\\webapps'
	 		echo 'copy success '
	  }catch( err) {
	       echo  err.getMessage()
              echo "Error detected, but we will continue."
		   emailext attachLog: true, body: '''Hi Developer,

something went wrong 
error trace $err''', compressLog: true, subject: '[MyProject]: Error in the uploading the files ', to: 'naveen2test@gmnail.com'
	      
	  }
  
  }

}
