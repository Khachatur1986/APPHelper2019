<?php

class Logger
{

    protected $fh;

    public function __construct()
    {
        $this->fh = fopen('log.txt', 'a+');
    }

    public function log($msg)
    {
        if (!$this->fh) {
            throw new Exception('Unable to open log file for writing');
        }
        if (fwrite($this->fh, $msg . "\n") === false) {
            throw new Exception('Unable to write to log file.');
        }
    }

    public function logAll()
    {
        log(date('m-d-Y H:i:s') . ' ' . $_SERVER['REMOTE_ADDR']);
        log('$_POST: ' . print_r($_POST, true));
        log('$_GET: ' . print_r($_GET, true));
        log('$_FILES: ' . print_r($_FILES, true));
//        log('file_get_contents: ' . print_r(file_get_contents('php://input'), true));
    }

    public function __destruct()
    {
        fclose($this->fh);
    }
}

//$logger = new Logger();
//$logger->log(date('m-d-Y H:i:s') . ' ' . $_SERVER['REMOTE_ADDR']);
//$logger->log('$_POST: ' . print_r($_POST, true));
//$logger->log('$_GET: ' . print_r($_GET, true));
//$logger->log('$_FILES: ' . print_r($_FILES, true));