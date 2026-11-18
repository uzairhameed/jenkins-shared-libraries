def call(){
  timeout(time: 1, unit: "MINUTES"){
    waitforQualityGate abortPipeline: false
  }
}
  
