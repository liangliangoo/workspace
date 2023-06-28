export default {
    assignNeed: (orgin = {}, target = {}) => {
        var matchResults = {};
        Object.keys(target).forEach(key=>{
            if(!Object.prototype.hasOwnProperty.call(orgin, key)){
                console.log('orgin not existed key:'+key);
            }
            matchResults[key] = orgin[key];
        })
        return matchResults;
    },
    //js对象=赋值默认是引用赋值,下面方式是非引用拷贝
    cloneObjectPros: (obj) => {
        return JSON.parse(JSON.stringify(obj));
    },
    //时间格式转化
    timeFormat :(timeVal) =>{
        let date = new Date(Number(timeVal));
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        let s = date.getSeconds();
        s = s < 10 ? ('0' + s) : s;
        return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
    },
    timeFormatToDate :(timeVal) =>{
        if(timeVal == null || timeVal == ''){return '';}
        let date = new Date(Number(timeVal));
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + MM + '-' + d;
    },
    truncateObject :(obj, skipKeys) =>{
        for (var k in obj) {
            if(skipKeys.indexOf(k) > -1){
                continue;
            }
            var ctor = obj[k] && obj[k].constructor; 
            if  (ctor === Object) this.truncateObject(obj[k], skipKeys); 
            else if (ctor === Number) delete obj[k]; 
            else if (ctor)   obj[k] = ctor(); 
        }
    },
    //获得当前的时和分HH:
    getHHmm: () =>{
        let date = new Date();
        let minutes = date.getMinutes();
        let hours = date.getHours();
        if(minutes < 10){
            minutes = '0' + minutes;
        }
        return hours + ":" + minutes;
    },


}