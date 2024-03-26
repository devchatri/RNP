<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
    crossorigin="anonymous">
  <link rel="stylesheet" href="css/main.css">
  <title>Fancy Form</title>
</head>

<body>
  <div id="container">
    <h1 class="logo">Registre National de La population</h1>
    <div id="form-box">
      <i id="prev-btn" class="fas fa-arrow-left"></i>
      <i id="next-btn" class="fas fa-arrow-right"></i>
      <div id="input-group">
          <form id="emailForm" action="preform" method="POST">
              <input type="email" id="input-field" name="email" required>
        <label id="input-label" ></label>
        <div id="input-progress" name="submit"></div>
        </form>
      </div>
      <div id="progress-bar"></div>
    </div>
  </div>

  <script src="js/main.js"></script>
</body>

</html>