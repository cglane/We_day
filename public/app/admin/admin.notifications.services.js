(function(){
  "use strict"
  angular
    .module('admin')
    .factory('NotServices',function($http){
      var urlN = "https://tiny-tiny.herokuapp.com/collections/not";
        var getNot = function(el){
          return $http.get(urlN);
        };
        var postNot = function(el){
          return $http.post(urlN,el);
        }
        var deleteNot = function(el){
          return $http.delete(urlN+'/'+el._id)
        }
    return{
      deleteNot:deleteNot,
      getNot:getNot,
      postNot:postNot,
    };
  });
})();