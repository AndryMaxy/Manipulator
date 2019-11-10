@echo off
netsh advfirewall firewall add rule name="Open Port 4448" dir=in action=allow protocol=UDP localport=4448