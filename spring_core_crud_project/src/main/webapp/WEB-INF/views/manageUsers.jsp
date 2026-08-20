<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html >
<head>
    <title>Manage Users</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container { 
            max-width: 1200px; 
            margin: 0 auto; 
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 30px;
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
            border-bottom: 3px solid #667eea;
            padding-bottom: 20px;
        }
        h1 { 
            color: #333;
            font-size: 2.5em;
            margin-bottom: 5px;
        }
        .subtitle { color: #666; font-size: 14px; }
        
        /* Form Section */
        .form-section { 
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            padding: 25px;
            margin-bottom: 30px;
            border-radius: 8px;
            border-left: 5px solid #667eea;
        }
        .form-title {
            font-size: 1.5em;
            color: #333;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }
        .form-title::before {
            content: "✎";
            margin-right: 10px;
            font-size: 1.3em;
            color: #667eea;
        }
        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 20px;
        }
        .form-group { 
            display: flex;
            flex-direction: column;
        }
        label { 
            font-weight: 600; 
            margin-bottom: 8px;
            color: #333;
            font-size: 14px;
        }
        input[type="text"], 
        input[type="number"], 
        input[type="email"],
        input[type="hidden"] { 
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        input[type="text"]:focus, 
        input[type="number"]:focus,
        input[type="email"]:focus { 
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
            outline: none;
        }
        .button-group {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }
        button, a button { 
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        .btn-add, .btn-submit { 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-add:hover, .btn-submit:hover { 
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .btn-reset { 
            background: #ff9800; 
            color: white;
        }
        .btn-reset:hover { 
            background: #e68900;
            transform: translateY(-2px);
        }
        .btn-cancel {
            background: #999;
            color: white;
        }
        .btn-cancel:hover {
            background: #777;
        }
        
        /* Filter Section */
        .filter-section { 
            background: #e8f5e9;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 8px;
            border-left: 5px solid #4CAF50;
        }
        .filter-title {
            font-size: 1.2em;
            color: #333;
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        .filter-title::before {
            content: "🔍";
            margin-right: 10px;
        }
        .filter-row { 
            display: flex; 
            gap: 15px; 
            flex-wrap: wrap; 
            align-items: flex-end;
        }
        input[type="search"] { 
            flex: 1;
            min-width: 200px;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        input[type="search"]:focus {
            border-color: #4CAF50;
            outline: none;
        }
        select {
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }
        .btn-search { 
            background: #4CAF50;
            color: white;
        }
        .btn-search:hover { 
            background: #45a049;
            transform: translateY(-2px);
        }
        
        /* Results Info */
        .results-info { 
            margin: 15px 0;
            padding: 10px 15px;
            background: #f0f0f0;
            border-radius: 5px;
            font-size: 14px;
            color: #666;
            font-weight: 500;
        }
        
        /* Table Section */
        .table-section {
            margin-top: 30px;
        }
        .table-title {
            font-size: 1.5em;
            color: #333;
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        .table-title::before {
            content: "📋";
            margin-right: 10px;
        }
        table { 
            width: 100%;
            border-collapse: collapse;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        th { 
            padding: 15px;
            text-align: left;
            font-weight: 600;
            border-bottom: 3px solid #667eea;
        }
        td { 
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        tbody tr { 
            transition: background 0.3s ease;
        }
        tbody tr:hover { 
            background: #f5f5f5;
        }
        tbody tr:nth-child(even) { 
            background: #fafafa;
        }
        .actions { 
            text-align: center;
            white-space: nowrap;
        }
        .action-buttons {
            display: flex;
            gap: 8px;
            justify-content: center;
        }
        .btn-edit, .btn-delete { 
            padding: 8px 12px;
            font-size: 12px;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s ease;
        }
        .btn-edit { 
            background: #2196F3; 
            color: white;
        }
        .btn-edit:hover { 
            background: #0b7dda;
            transform: scale(1.05);
        }
        .btn-delete { 
            background: #f44336; 
            color: white;
        }
        .btn-delete:hover { 
            background: #da190b;
            transform: scale(1.05);
        }
        
        /* Empty State */
        .empty-state {
            text-align: center;
            padding: 40px;
            color: #999;
        }
        .empty-state-icon {
            font-size: 3em;
            margin-bottom: 10px;
        }
        
        /* Success/Error Messages */
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-weight: 500;
        }
        .alert-success {
            background: #d4edda;
            color: #155724;
            border-left: 4px solid #28a745;
        }
        .alert-error {
            background: #f8d7da;
            color: #721c24;
            border-left: 4px solid #f5c6cb;
        }
        
        /* Responsive */
        @media (max-width: 768px) {
            .container { padding: 15px; }
            h1 { font-size: 1.8em; }
            .form-grid { grid-template-columns: 1fr; }
            .filter-row { flex-direction: column; }
            input[type="search"] { width: 100%; }
            table { font-size: 13px; }
            th, td { padding: 10px; }
            .action-buttons { flex-direction: column; }
        }
    </style>

</head>
<body>
    <div class="container">
        <div class="header">
            <h1>👥 User Management System</h1>
            <p class="subtitle">Manage your users efficiently with add, edit, and delete operations</p>
        </div>

        <!-- Add/Edit User Form -->
        <div class="form-section">
            <div class="form-title">
                <c:choose>
                    <c:when test="${user != null && user.id != null}">Edit User</c:when>
                    <c:otherwise>Add New User</c:otherwise>
                </c:choose>
            </div>
            
            <form:form method="post" 
                       action="${user != null && user.id != null ? '/spring_core_crud_project/users/update' : '/spring_core_crud_project/users/add'}" 
                       modelAttribute="user">
                
                <c:if test="${user != null && user.id != null}">
                    <form:hidden path="id"/>
                </c:if>

                <div class="form-grid">
                    <div class="form-group">
                        <label for="firstName">First Name *</label>
                        <form:input path="firstName" id="firstName" placeholder="Enter first name" required="required"/>
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last Name *</label>
                        <form:input path="lastName" id="lastName" placeholder="Enter last name" required="required"/>
                    </div>

                    <div class="form-group">
                        <label for="age">Age *</label>
                        <form:input path="age" id="age" type="number" placeholder="Enter age" min="1" max="150" required="required"/>
                    </div>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn-submit">
                        <c:choose>
                            <c:when test="${user != null && user.id != null}">✓ Update User</c:when>
                            <c:otherwise>✓ Add User</c:otherwise>
                        </c:choose>
                    </button>
                    <c:if test="${user != null && user.id != null}">
                        <a href="/spring_core_crud_project/users"><button type="button" class="btn-cancel">✕ Cancel</button></a>
                    </c:if>
                </div>
            </form:form>
        </div>

        <!-- Users Table Section -->
        <div class="table-section">
            <div class="table-title">All Users</div>
            
            <c:choose>
                <c:when test="${empty users}">
                    <div class="empty-state">
                        <div class="empty-state-icon">📭</div>
                        <p>No users found. Start by adding a new user.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table id="usersTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Age</th>
                                <th class="actions">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td><strong>#${user.id}</strong></td>
                                    <td><c:out value="${user.firstName}"/></td>
                                    <td><c:out value="${user.lastName}"/></td>
                                    <td><c:out value="${user.age}"/></td>
                                    <td class="actions">
                                        <div class="action-buttons">
                                            <a href="/spring_core_crud_project/users/edit/${user.id}" class="btn-edit">✏️ Edit</a>
                                            <a href="/spring_core_crud_project/users/delete/${user.id}" 
                                               class="btn-delete" 
                                               onclick="return confirm('Are you sure you want to delete this user? This action cannot be undone.');">
                                               🗑️ Delete
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
