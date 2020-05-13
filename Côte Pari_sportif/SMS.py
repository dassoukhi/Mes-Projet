
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText



def SMS(message):


    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.ehlo()

    server.login('YOUR MAIL', 'YOUR PASSWORD')

    msg = MIMEMultipart('alternative')
    msg['Subject'] = "combi"
    msg['From'] = "YOUR MAIL"
    msg['To'] = 'YOUR MAIL FOR SEND'

    text = message

    part1 = MIMEText(text, 'plain')

    msg.attach(part1)


    server.sendmail('YOUR MAIL', 'YOUR MAIL TO SEND', msg.as_string())
    print("reussi")
    server.quit()
