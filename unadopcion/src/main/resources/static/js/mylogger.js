var consoleText = ""
// define a new console
window.console = (function(console){
    return {
        log: function(text){
            console.log(text);
            consoleText = mydate(text);
        },
        info: function (text) {
            console.info(text);
            consoleText += text;
        },
        warn: function (text) {
            console.warn(text);
            consoleText += text;
        },
        error: function (text) {
            console.error(text);
            consoleText += text;
        }
    };
}(window.console));

function mydate(text){
    var hoy = new Date()
    consoleText += "[" + hoy.getDate() + '-' + (hoy.getMonth() + 1) + '-' + hoy.getFullYear() + "]";
    consoleText += "[" + hoy.getHours() + ":" + hoy.getMinutes() + ":" + hoy.getSeconds() + "]";
    consoleText += text;
    consoleText += "\n"
    return consoleText;
}

window.addEventListener('error', function(event) {
    consoleText = mydate(event.message);
    download("log.txt", consoleText)
})


function download(filename, text) {
    var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    pom.setAttribute('download', filename);
    document.body.appendChild(pom);
    pom.click();
    document.body.removeChild(pom);
  }