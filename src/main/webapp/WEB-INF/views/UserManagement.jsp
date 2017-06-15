<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>AngularJS $http Example</title>
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="fname">First Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.firstName" id="fname" class="username form-control input-sm" placeholder="enter your first name" required ng-minlength="1"/>
                                  <div class="has-error" ng-show="myform.$dirty">
                                      <span ng-show="myform.fname.$error.required">this is a required field</span>
                                      <span ng-show="myform.fname.$error.minlength">minimum length required is 1</span>
                                      <span ng-show="myform.fname.$invalid">this field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                     <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="lname">Last Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.lastName" id="lname" class="username form-control input-sm" placeholder="enter your last name" required ng-minlength="1"/>
                                  <div class="has-error" ng-show="myform.$dirty">
                                      <span ng-show="myform.lname.$error.required">this is a required field</span>
                                      <span ng-show="myform.lname.$error.minlength">minimum length required is 1</span>
                                      <span ng-show="myform.lname.$invalid">this field is invalid </span>
                                  </div>
                              </div>
                          </div>
                     </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="dateB">Date Birthday</label>
                              <div class="col-md-7">
                                  <input type="date" ng-model="ctrl.user.dateBirthday" id="dateB" class="date form-control input-sm"/>
                                  <div class="has-error" ng-show="myform.$dirty">
                                      <span ng-show="myform.uname.$error.required">this is a required field</span>
                                      <span ng-show="myform.uname.$invalid">this field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="loginU">Login</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.login" id="loginU" class="username form-control input-sm" placeholder="enter your login" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myform.$dirty">
                                      <span ng-show="myform.uname.$error.required">this is a required field</span>
                                      <span ng-show="myform.uname.$error.minlength">minimum length required is 3</span>
                                      <span ng-show="myform.uname.$invalid">this field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="password">Password</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.password" id="password" class="username form-control input-sm" placeholder="enter your password" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myform.$dirty">
                                      <span ng-show="myform.uname.$error.required">this is a required field</span>
                                      <span ng-show="myform.uname.$error.minlength">minimum length required is 3</span>
                                      <span ng-show="myform.uname.$invalid">this field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="address">Address</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.residence" id="address" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="about">About user:</label>
                              <div class="col-md-7">
                                  <textarea ng-model="ctrl.user.aboutUser" id="about" class="form-control input-sm" placeholder="Enter information about you. [This field is validation free]"></textarea>
                              </div>
                          </div>
                      </div>



                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>fistName</th>
                              <th>lastName</th>
                              <th>dateBirthday</th>
                              <th>login</th>
                              <th>password</th>
                              <th>residence</th>
                              <th>aboutUser</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.usersFetch">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.firstName"></span></td>
                              <td><span ng-bind="u.lastName"></span></td>
                              <td><span ng-bind="u.dateBirthday"></span></td>
                              <td><span ng-bind="u.login"></span></td>
                              <td><span ng-bind="u.password"></span></td>
                              <td><span ng-bind="u.residence"></span></td>
                              <td class="aboutUs"><span ng-bind="u.aboutUser"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>

      <script src="<c:url value='/static/js/angular.js' />"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
  </body>
</html>