# socialmedia-rest-api
inspired by users to manipute posts and basic crud application

rest api end points:



postmapping:

/jpa/users/{id}/posts=>{user can post under user_id}

Delete mapping:

/jpa/users/posts/{id} =>(deletes the post which has post_id)

/jpa/users/{id}=>{deltes all the posts and user from both postRepository and userRepositoery }


Putmapping:

jpa/users/{id}/posts/{pid}=>editing the user's post with new description.


Getmapping:
http://localhost:8080/jpa/users (retrives the all the signed up users)

http://localhost:8080/users/{id}(retrives users posts as per the user_id)
