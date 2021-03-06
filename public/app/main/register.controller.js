(function(){
"use strict"
angular
  .module('main')
  .controller('RegisterController',function($route,$window,$location,$scope,RegisterService){
    $scope.passwordsAreSame = true;
    var users = [];
    $scope.emailAlreadyExists = false;
    $scope.emailExists = function(email){
      _.each(users,function(el){
        return el.email === email ? true :false;
      })
    };
    $scope.passwordEmailMatch = function(email,password){
      _.each(users,function(el){
        if(el.email === email){
          if(el.password === password){
            localStorage.setItem('currentUser',el.username);
            return true;
          }else{
            return false;
          }
        }
      })
    }
    // $scope.getUsers = function(){
    //   RegisterService.getAllUsers().success(function(res){
    //     users = res;
    //     console.log('all users',res)
    //   })
    // };
    var continueBool = false;
    $scope.canContinue = function(){
      return continueBool === true ? 2 : 1
    };
    $scope.addNewUser = function(){
      var currObj = {
        username: $scope.userName,
        email: $scope.email,
        phone: $scope.phone,
        password: $scope.password
      }
      if($scope.password == $scope.passwordAuth){
        RegisterService.addNewUser(currObj).success(function(res){
          console.log('res createUSer',res)
          res[1] ? $scope.setCurrentView(2): $scope.emailAlreadyExists = true
        });
      }
      else{alert('wrong password')};
    };
    $scope.createAdminInputsEmpty = function(){
      console.log('createAdminInputsEmpty')
      if($scope.userName && $scope.email && $scope.phone&& $scope.password){
        return true;
      }else{
        return false;
      }
    };
    $scope.passwordsSame = function(){
      console.log('passwords the same');
      if($scope.password == $scope.passwordAuth){
        return true;
      }else{
        $scope.passwordsAreSame = false;
        return false
      }
    }
  });
})();
