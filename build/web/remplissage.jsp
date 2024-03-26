<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="number"],
        select,
        input[type="date"],
        input[type="file"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Formulaire de soumission</h2>
    <form id="myForm" action="MonServlet" method="POST" enctype="multipart/form-data">
        <label for="firstName">Prénom:</label><br>
        <input type="text" id="firstName" name="firstName"><br>
        <label for="lastName">Nom de famille:</label><br>
        <input type="text" id="lastName" name="lastName"><br>
        <label for="age">Âge:</label><br>
        <input type="number" id="age" name="age" min="0"><br>
        <label for="country">Pays:</label><br>
        <select id="country" name="country">
            <option value="France">France</option>
            <option value="Allemagne">Allemagne</option>
            <option value="Royaume-Uni">Royaume-Uni</option>
            <!-- Ajoutez d'autres options selon vos besoins -->
        </select><br>
        <label for="dob">Date de naissance:</label><br>
        <input type="date" id="dob" name="dob"><br>
        <label for="file">Fichier:</label><br>
        <input type="file" id="file" name="file"><br><br>
        <input type="submit" value="Envoyer">
    </form>
</body>
</html>
