import HTMLParser
from datetime import datetime
import random

class htmlParser(HTMLParser.HTMLParser):
    def __init__(self):
        HTMLParser.HTMLParser.__init__(self)
        self.recording = 0
        self.data = []
        
    def handle_starttag(self, tag, attrs):
        if self.recording == 1:
            for name, value in attrs:
                print name + " > " + value
                
        if tag != 'input':
            return
    
        self.recording = 1 
    def handle_endtag(self, tag):
        if tag != 'input':
            return
        
        self.recording = 0
        return
    
    def handle_data(self, data):
        if self.recording == 1:
            print data
        return