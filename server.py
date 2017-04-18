server.py
root@raspberrypi2 ~/projects/gpio_server # cat webiopi_custom.py
# Imports
import webiopi
import time
# Retrieve GPIO lib
GPIO = webiopi.GPIO
# -------------------------------------------------- #
# Macro definition part                              #
# -------------------------------------------------- #
# A custom macro which prints out the arg received and return OK
def myMacroWithArgs(arg1, arg2, arg3):
    print("myMacroWithArgs(%s, %s, %s)" % (arg1, arg2, arg3))
    return "OK"
# A custom macro without args which return nothing
def myMacroWithoutArgs():
    print("myMacroWithoutArgs()")
# Example loop which toggle GPIO 7 each 5 seconds
def loop():
    time.sleep(5)        
def turnLed(port_str, ms):
    port = int(port_str)
    GPIO.setFunction(port,GPIO.OUT)    
    GPIO.output(port,GPIO.LOW)
    time.sleep(float(ms)/1000)
    GPIO.output(port,GPIO.HIGH)
def turnWebcam(steps_str, clockwise_str):
    steps = int(steps_str);
    clockwise = int(clockwise_str);
    arr = [0,1,2,3];
    if clockwise!=1:
        arr = [3,2,1,0];
    ports = [17,18,27,22]
    for p in ports:
        GPIO.setFunction(p,GPIO.OUT)
    for x in range(0,steps):
        for j in arr:
            time.sleep(0.01)
            for i in range(0,4):
                if i == j:            
                    GPIO.output(ports[i],GPIO.LOW)
                else:
                    GPIO.output(ports[i],GPIO.HIGH)
# -------------------------------------------------- #
# Initialization part                                #
# -------------------------------------------------- #
# Setup GPIOs
# -------------------------------------------------- #
# Main server part                                   #
# -------------------------------------------------- #
# Instantiate the server on the port 8000, it starts immediately in its own thread
server = webiopi.Server(port=8001, login="pi", password="pi")
# or     webiopi.Server(port=8000, passwdfile="/etc/webiopi/passwd")
# Register the macros so you can call it with Javascript and/or REST API
server.addMacro(turnWebcam)
server.addMacro(turnLed)
# -------------------------------------------------- #
# Loop execution part                                #
# -------------------------------------------------- #
# Run our loop until CTRL-C is pressed or SIGTERM received
webiopi.runLoop()
# If no specific loop is needed and defined above, just use 
# webiopi.runLoop()
# here instead
# -------------------------------------------------- #
# Termination part                                   #
# -------------------------------------------------- #
# Cleanly stop the server
server.stop()
