<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "UPDATE setor SET tglsetor='$_POST[tglsetor]',nik='$_POST[nik]',nama='$_POST[nama]',qtyplastik='$_POST[qtyplastik]',qtybesi='$_POST[qtybesi]',total='$_POST[total]' WHERE id='$_POST[id]'";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
