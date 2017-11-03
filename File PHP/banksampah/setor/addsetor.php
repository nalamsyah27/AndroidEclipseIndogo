<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "INSERT INTO setor(tglsetor,nik,nama,qtyplastik,qtybesi,total) VALUES('$_POST[tglsetor]','$_POST[nik]','$_POST[nama]','$_POST[qtyplastik]','$_POST[qtybesi]','$_POST[total]')";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
