import HTMLParser
from datetime import datetime
import random

class htmlParser(HTMLParser.HTMLParser):
    def __init__(self):
        HTMLParser.HTMLParser.__init__(self)
        self.recording = 0
        self.field = ''
        self.data = []
        self.randomWalk = random.randint(1, 10)
        
    def handle_starttag(self, tag, attrs):
        if self.recording and tag == 'td':
            for name, value in attrs:
                #print name + " > " + value
                if value == 'option' or value == 'depart' or value == 'arrive' or value == 'duration':
                    #print "for: > " + value
                    self.field = value
                    
        if tag != 'table':
            return
        
        for name, value in attrs:
            self.recording = 1
            
    def handle_endtag(self, tag):
        if tag != 'table':
            return
        
        self.recording = 0
        self.field = ''
        return
    
    def handle_data(self, data):
        if self.recording == 1 and self.field != '' and data != " " and data != 'View':
            #print self.field + " > " + data
            self.data.append( {'field': self.field, 'val': data} )
            
            if self.field == "duration":
                remTime = data
                try:              
                    rem = datetime.strptime(data, "%H:%M") - datetime.strptime( str(self.randomWalk), "%M")
                    remTime = rem.seconds / 60
                except:
                    pass
                
                self.data.append( {'path': [{'field': "walk", 'val': self.randomWalk}, {'field': "tube", 'val': remTime}] } )
        self.recording2 = ''
        return