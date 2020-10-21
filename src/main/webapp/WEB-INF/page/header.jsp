<header style="background-color: #55b4ff;">
		<h1>Hi ${userData.name}</h1>
		</header>
		<br>
		<div class="container" style="border-bottom: 1px solid #55b4ff;">
			<img src="${sessionScope.userData.photo }" height=200px align=right> <br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<a href="manage"><button type="button" class="btn btn-primary">Manage Users</button></a>
			<button type="reset" class="btn btn-danger">Email</button>
			<a href="displayprofile"><button type="button" class="btn btn-success">Profiles</button></a>
			<button type="button" class="btn btn-warning">Block Times</button>
			<a href="loggedUser"><button type="button" class="btn btn-success">Logged in User</button></a>
			<a href="logout">
				<button type="button" class="btn btn-warning">Logout</button>
			</a>
		</div>
		<br>
