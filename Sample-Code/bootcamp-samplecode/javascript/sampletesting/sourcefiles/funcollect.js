var users={}

function initializeUsers(userall)
{
 users=userall;
 return users;
}

function searchUser(userlist,key)
{
    return userlist[key];
}

module.exports={initializeUsers,searchUser}