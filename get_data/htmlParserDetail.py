import HTMLParser
from datetime import datetime
import random
import re

class htmlParser(HTMLParser.HTMLParser):
    def __init__(self):
        HTMLParser.HTMLParser.__init__(self)
        self.recording = 0
        self.field = ''
        self.data = []
        self.data2 = []
        self.info = []
        self.info2 = ''
        self.time = []
        self.hasnode = 0
        self.node = ''
        self.hastime = 0
        self.timegap = 0
        self.type = ''
        
    def handle_starttag(self, tag, attrs):
        if self.recording:
            for name, value in attrs:
                if 'XSLT_REQUEST' in value:

                    time = ''
                    if len(self.time) > 0 and self.timegap == 0:
                        time = self.time.pop(0)
                    
                    #print time + ' > ' + str(self.info2)
                    self.hasnode = 0
                    self.data2.append( {'time': time, 'type': self.type, 'node': self.node, 'details': self.info2} )
                    
                    self.info2 = ''
                if 'zoneinfo' in value:
                    self.recording = 0
                #print '*' + value + ' > ' + name
                
                if 'assets/images/icon-buses.gif' in value:
                    self.type = 'bus'
                if 'assets/images/icon-walk.gif' in value:
                    self.type = 'walk'
                if 'assets/images/icon-tube.gif' in value:
                    self.type = 'tube'
                
                if 'assets/images/icon-buses.gif' in value or 'assets/images/icon-walk.gif' in value or 'assets/images/icon-tube.gif' in value:
                    if self.hastime == 1:
                        self.timegap = 1
        
        if tag != 'td':
            return
        
        self.hastime = 0
        self.recording = 1
            
    def handle_endtag(self, tag):
        if tag != 'td':
            return
        
        self.recording = 0
        self.field = ''
        return
    
    def handle_data(self, data):
        if 'Maximum journey time' in data:
            #print '---------------------------------------------------------------------------'
            self.data.append( {'option': self.data2} )
            self.data2 = []
    
        if self.recording == 1:
            r = re.compile(r"^\s+", re.MULTILINE)
            data = r.sub(" ", data)
            data = re.sub('\n', '', data)
            
            first = '?'
            if len(data) > 1:
                first = str(data)[0]
            
            if first >= '0' and first <= '9':

                #start a new field
                self.hastime = 1
                
                if self.timegap == 1:
                    prev = self.time[ len(self.time) - 1 ]
                    self.time[ len(self.time) - 1 ] = prev + ' - ' + data
                    self.timegap = 0
                else:
                    self.time.append(data)

            else:
                if self.hasnode == 0 and data != ' ':
                    self.node = data
                    self.hasnode = 1
                self.info2 += data + ' ' 
            
        return