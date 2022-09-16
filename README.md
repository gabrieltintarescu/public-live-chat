

![GitHub followers](https://img.shields.io/github/followers/gabrieltintarescu?style=social)
![Logo](https://gabrieltintarescu.com/wp-content/uploads/2022/09/ChatBoix_Logo.png)


# 💬 Public Live Chat App
Real-time multi-platform chat app with user accounts with roles and admin functions.

## ✒️ Authors

- [@gabrieltintarescu](https://www.github.com/gabrieltintarescu)


### 💻 Technologies
- Java + Spring Boot
- Dart + Flutter
- Socket.IO
- MySQL
### 📖 To Do’s
-	Chat & User Models + Roles + Database
-	User registration
-	User Login
-	Chat functionality
-	Admin Functionality
### Functionality Planning:
-	User creates account with MySQL model and logins using REST API in Spring Boot
-	User successfully logs in -> receives a jwt Token according to his role.
-	User then is connected to the Socket.IO server ready to send messages to the group chat.
### Mute Functionality:
-	Moderator types /mute “Username” x (minutes) in chat, server checks for user privilege and if user is moderator/admin, sends a new JwtToken with isMuted flag to muted user with expiration set to x minutes.
-	Mute will expire when JwtToken will expire and will create new token with refreshtoken
### Ban Functionality:
-	Moderator types /ban “Username” in chat, server checks for user privilege and if user is admin, flags user as banned in database then forces user to disconnect from server. At re-authentication user will not be granted into the application.
### Models
-	Jwt Token contains: Username, User Role and IsMuted
-	User model contains: Username, Email, Password, Role, isBanned



## 🌐 API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://gabrieltintarescu.com/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)

