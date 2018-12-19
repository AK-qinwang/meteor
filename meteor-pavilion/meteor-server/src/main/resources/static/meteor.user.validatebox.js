$.extend($.fn.validatebox.defaults.rules, {
    checkLoginName: {
        validator: function(value,param){
            var minlength = param[0];
            var maxlength = parseInt(param[1]);
            if(value.length > maxlength){
                param[0]="长度不能大于"+maxlength;
                return false;
            }//^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$
            var reg=new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"+minlength+","+maxlength+"}$");
            param[0]=param[2];
            return reg.test(value);
        },
        message: '{0}'
    }
});