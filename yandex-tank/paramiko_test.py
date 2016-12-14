import logging
from paramiko import SSHClient, AutoAddPolicy

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(levelname)s %(message)s')

logging.getLogger("paramiko.transport").setLevel(logging.DEBUG)

logger = logging.getLogger(__name__)

class SecuredShell(object):
    def __init__(self, host, port, username, timeout):
        self.host = host
        self.port = port
        self.username = username
        self.timeout = timeout

    def __connect(self):
        logger.debug("Opening SSH connection to {host}:{port}".format(
            host=self.host, port=self.port))
        client = SSHClient()
        client.load_system_host_keys()
        client.set_missing_host_key_policy(AutoAddPolicy())
        client.connect(
            self.host,
            port=self.port,
            username=self.username,
            timeout=self.timeout,
        )
        return client

    def execute(self, cmd):
        with self.__connect() as client:
            _, stdout, stderr = client.exec_command(cmd)
            output = stdout.read()
            errors = stderr.read()
        return output, errors

ssh = SecuredShell("192.168.0.105", 22, "user", 10)
print ssh.execute("ls -l")
