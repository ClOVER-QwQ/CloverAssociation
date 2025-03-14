const express = require('express');
const app = express();
app.use(express.static('./dist'));

//运行时的端口，可以自己自定义
const port = 80;

app.listen(port, function (err) {
    if (err) {
        console.log(err);
        return;
    }
    console.log('Listening at http://localhost:' + port + '\n');
});

