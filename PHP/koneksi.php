<?php
	define("DB_HOST","localhost");
	define("DB_USER","root");
	define("DB_PASS","");
	define("DB_NAME","gis");
	
	$db = new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	
	if($db->connect_errno)
	{
		echo "Connection Error : ".$db->connect_errno;
	}
?>