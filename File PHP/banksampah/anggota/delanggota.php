<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "DELETE FROM anggota WHERE id = '$_GET[id]'";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
