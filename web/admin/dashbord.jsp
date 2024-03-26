<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %> <!-- Ajout de l'importation -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <style>
        body {margin:2em;}
        td:last-child {text-align:center;}
    </style>
</head>
<body>

<!-- As a heading -->
<nav class="navbar navbar-light bg-light">
  <span class="navbar-brand mb-0 h1">Registre National De La Population</span>
  <span class="navbar-brand mb-0 h1">ADMIN</span>
</nav>
<a class="btn btn-success" style="float:left;margin-right:20px;" href="/RNP/logout" >Se Déconnecter</a>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Date</th>
			<th>Status</th>
			<th style="text-align:center;width:100px;">Actions</th>
		</tr>
	</thead>
	<tbody>
        <% 
            ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
            if(resultSet != null) {
                while(resultSet.next()) {
        %>
		<tr>
			<td><%= resultSet.getInt("info_id") %></td>
			<td><%= resultSet.getString("nom") %></td>
            <td><%= resultSet.getString("prenom") %></td>
			<td><%= resultSet.getString("date_rdv") %></td>
			<td><%= resultSet.getString("status") %></td>
			<td>
                <form action="EditRecordServlet" method="POST" style="display: inline;">
                    <input type="hidden" name="id" value="<%= resultSet.getInt("info_id") %>">
                    <button type="submit" class="btn btn-primary btn-xs" style="margin-right:16px;">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Confirmer
                    </button>
                </form>
                <form action="DeleteRecordServlet" method="POST" onsubmit="return confirm('Are you sure you want to delete this record?');" style="display: inline;">
                    <input type="hidden" name="id" value="<%= resultSet.getInt("info_id") %>">
                    <button type="submit" class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Delete
                    </button>
                </form>
            </td>
		</tr>
        <% 
            }
            resultSet.close();
        } else {
        %>
        <% 
            }
        %>		
	</tbody>
</table>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Row information</h4>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script>
    // JavaScript code here
    $(document).ready(function() {
        // DataTable initialisation
        $('#example').DataTable(
            {
                "dom": '<"dt-buttons"Bf><"clear">lirtp',
                "paging": false,
                "autoWidth": true,
                "columnDefs": [
                    { "orderable": false, "targets": 5 }
                ],
                "buttons": [
                    'colvis',
                    'copyHtml5',
                    'csvHtml5',
                    'excelHtml5',
                    'pdfHtml5',
                    'print'
                ]
            }
        );

        $('#myModal').on('hidden.bs.modal', function (evt) {
            $('.modal .modal-body').empty();
        });
    });
</script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
