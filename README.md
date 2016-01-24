Creates a Java applet containing strings (consisting of one character, either
 * R, P, S, L, or K) that move across the applet in a given direction forever,
 * wrapping around the borders of the applet. These strings are constructed
 * based on information provided by the HTML document, BannerApplet.html. If two
 * of these strings collide with each other, depending on the string itself, one
 * of the strings will be destroyed or they may continue on harmlessly as
 * according to the rules of RPSLK. Essentially, a game of RPSLK is played
 * between the two Strings to determine who the survivor is. If there is a tie
 * between the two Strings, the strings will continue on as though nothing
 * happened. Upon collision, a message is printed indicating the event that took
 * place between the strings; for example the intersection between a Rock and
 * Lizard (R and L) would produce the message "Rock crushes Lizard," while the
 * intersection between Paper and Rock (P and R) would produce the message
 * "Paper covers Rock."
