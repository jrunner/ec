// JavaScript Document
var  $c=function(array){var nArray = [];for (var i=0;i<array.length;i++) nArray.push(array[i]);return nArray;};
Array.prototype.each=function(func){
for(var i=0,l=this.length;i<l;i++) {func(this[i],i);};
};
document .getElementsByClassName=function(cn){
var hasClass=function(w,Name){
var hasClass = false;
w.className.split(' ').each(function(s){
if (s == Name) hasClass = true;
});
return hasClass;
}; 
var elems =document.getElementsByTagName("*")||document.all;
            var elemList = [];
           $c(elems).each(function(e){
if(hasClass(e,cn)){elemList.push(e);}
		   })
        return $c(elemList);
};		
function change_bg(obj){
var a=document.getElementsByClassName("nav")[0].getElementsByTagName("a");
for(var i=0;i<a.length;i++){a[i].className="";}
obj.className="nav_current";
}
