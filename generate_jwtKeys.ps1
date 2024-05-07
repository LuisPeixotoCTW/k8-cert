New-Item -ItemType Directory -Force -Path jwt
openssl genrsa -out .\jwt\rsaPrivateKey.pem 2048
openssl rsa -pubout -in .\jwt\rsaPrivateKey.pem -out .\jwt\publicKey.pem
openssl pkcs8 -topk8 -nocrypt -inform pem -in .\jwt\rsaPrivateKey.pem -outform pem -out .\jwt\privateKey.pem
icacls .\jwt\rsaPrivateKey.pem /inheritance:r /grant:r "$($env:USERNAME):(R)"
icacls .\jwt\privateKey.pem /inheritance:r /grant:r "$($env:USERNAME):(R)"