angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user = {id:null, firstName:'', lastName:'', dateBirthday: new Date(), login:'', password:'', aboutUser:'', residence:''};
    self.users = [];
    self.usersFetch = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllUsers();

    function fetchAllUsers(){
            UserService.fetchAllUsers()
                .then(
                function(d) {
                    self.users = d;
                    self.usersFetch = angular.copy(self.users);
                    self.usersFetch.forEach(cutUsersInf);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
        }

    function cutUsersInf(element, index, array) {
        element.firstName = element.firstName.substr(0, 20);
        element.lastName = element.lastName.substr(0, 20);
        element.login = element.login.substr(0, 20);
        element.password = element.password.substr(0, 20);
        element.residence = element.residence.substr(0, 20);
        element.aboutUser = element.aboutUser.substr(0, 20);
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
                console.log('COPY ID: ', self.user.dateBirthday);
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++) {
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                self.user.dateBirthday = parseDate(self.user.dateBirthday);
                break;
            }
        }
    }

    function parseDate(date) {
        var dateParts = date.split("-");

        dateInner = new Date(dateParts[1] + '/' + dateParts[2] + '/' + dateParts[0]);
        dateInner.setHours(17);

        return dateInner;
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
        fetchAllUsers();
        $scope.myForm.$setPristine(); //reset Form
    }

    function reset(){
        self.user={id:null, firstName:'', lastName:'', dateBirthday: new Date(), login:'', password:'', aboutUser:'', residence:''};
        $scope.myForm.$setPristine(); //reset Form
    }
}]);