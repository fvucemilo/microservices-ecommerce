$SONAR_HOST_URL = $env:SONAR_HOST_URL
$SONAR_PROJECT_KEY = $env:SONAR_PROJECT_KEY
$SONAR_LOGIN = $env:SONAR_LOGIN

(Get-Content pom.xml) | Foreach-Object {
    $_ -replace 'SONAR_HOST_URL', $SONAR_HOST_URL `
         -replace 'SONAR_PROJECT_KEY', $SONAR_PROJECT_KEY `
         -replace 'SONAR_LOGIN', $SONAR_LOGIN
} | Set-Content pom.xml
