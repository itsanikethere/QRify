# QRify

QRify is a REST API which can be used to create UPI payment QR codes. QRify accepts parameters inside the request body as JSON. Based on the parameters, QRify will create a QR code and encode its byte into a base64 string.

## Mandatory parameters:

- `vpa`: Virtual Payment Address of Payee
- `name`: Name of Payee

## Optional parameters:

- `notes`: Transaction notes (Up to 80 characters)
- `amount`: Transaction amount (Up to 2 decimals)
- `width`: QR code width (Default 500)
- `height`: QR code height (Default 500)
- `format`: QR code image format in PNG or JPG (Default PNG)

## Usage 💻

To use QRify, follow these steps:

1. Download the JAR file and ensure that you have JRE version 17 or higher installed on your computer.

2. Run the JAR file locally inside your terminal or command prompt using `java -jar QRify.jar`

3. Send a GET request to the endpoint `/api/QRify` with request body.

### Example Request (Python)

```python
import requests
import json

url = "http://localhost:8080/api/QRify"

payload = {
    "vpa": "aniketpanchal@upi",
    "name": "Aniket Panchal",
    "notes": "Food Payment",
    "amount": 100,
    "width": 500,
    "height": 500,
    "format": "PNG"
}

headers = {
    "Content-Type": "application/json"
}

response = requests.get(url, data=json.dumps(payload), headers=headers)

if response.status_code == 200:
    qr_code_base64 = response.json()["qr_code"]
    # Process the base64 string as needed
else:
    # Handle error response
```

---
Give QRify a try and make UPI payments with ease!
