
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText



def SMS(message):


    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.ehlo()

    server.login('maladealpha@gmail.com', 'grrkwwnlostrwgjk')

    msg = MIMEMultipart('alternative')
    msg['Subject'] = "combi"
    msg['From'] = "maladealpha@gmail.com"
    msg['To'] = 'maladealpha@gmail.com'

    text = message

    part1 = MIMEText(text, 'plain')

    msg.attach(part1)


    server.sendmail('maladealpha@gmail.com', 'maladealpha@gmail.com', msg.as_string())
    print("reussi")
    server.quit()
SMS("à l'ecole\nta mere ça race la chienne \n")
