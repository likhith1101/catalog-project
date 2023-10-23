<!DOCTYPE html>
<html>
<head>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
      transition: background-color 0.3s, color 0.3s;
    }
    th {
      background-color: #f2f2f2;
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    tr:hover {
      background-color: #ddd;
    }
    tr:hover td {
      background-color: #007BFF;
      color: #fff;
    }
  </style>
</head>
<body>
  <h2>Scrum Board with Animated Styles</h2>
  <table>
    <thead>
      <tr>
        <th>Status</th>
        <th>Task</th>
        <th>User Story</th>
        <th>Due Date</th>
        <th>Duration</th>
      </tr>
    </thead>
    <tbody>
      <!-- Your table data here -->
    </tbody>
  </table>
</body>
</html>
