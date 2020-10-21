<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Factorial</title>
</head>
<body>

	<header style="background-color: #55b4ff;">

		<h1>Factorial</h1>
	</header>
	<br>
	<form name="fact" action="fact" method="get">
		<div class="container">

			<img src="Image/1.png" height="300px" id="studentImage"> <span
				id="emessage"
				style="color: blue; font-size: 16px; font-weight: bold;"> </span>
			<h4>
				<label>Enter a number</label><br> <input name="num" type="text"
					style="width: 500px" id="num">
			</h4>


			<button type="submit" class="btn btn-primary"
				style="margin-right: 36px">Submit</button>
			<button type="reset" class="btn btn-secondary"
				style="margin-right: 36px" name="buttonName" value="reset">Reset</button>
			<br> <br> <br>
			<hr />
			<span id="message" style="color: red"> coming
				soon,...${resulta }</span>
		</div>

	</form>
</body>
</html>