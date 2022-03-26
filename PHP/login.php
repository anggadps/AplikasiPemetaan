<?php
    require_once('koneksi.php');

    if($_SERVER['REQUEST_METHOD'] == 'POST')
    {
        $user = $_POST['user'];
        $pass = $_POST['pass'];

        $query = $db->query("SELECT * FROM tb_user WHERE user = '$user' AND  $pass = '$pass'");

        if($query->num_rows > 0)
        {
            while ($row = $query->fetch_object()){
                $data = array("kode" => 1, "pesan" => "data ditemukan", "result" =>
                array(
                    array("nama" => $row->nama, "jenis_kelamin" => $row->jk, "user" => $row->username)
                ));
            
            echo json_encode($data);
            }
        }else
        {
            echo json_encode(array("kode"=>0, "pesan"=> "Data user tidak terdaftar"));
        }

    }
    else
    {
        echo json_encode(array("kode"=>0, "pesan"=> "request tidak valid"));
    }

?>