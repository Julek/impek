import urllib
import urllib2
import htmlParser
import htmlParserDetail
import json

url = 'http://journeyplanner.tfl.gov.uk/user/XSLT_TRIP_REQUEST2'

user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'

values = {
    'language': 'en',
    'execInst': '',
    'sessionID': '0',
    'ptOptionsActive': '-1',
    'place_origin': 'London',
    'place_destination': 'London',
    'type_origin': 'stop',
    'type_destination': 'stop',
    'name_origin' : 'South Kensington',
    'name_destination': 'Victoria'       
}

headers = { 'User-Agent' : user_agent }

data = urllib.urlencode(values)
req = urllib2.Request(url, data)
response = urllib2.urlopen(req)
the_page = response.read()

f = open('response.html', 'r')
data = f.read()

myparser = htmlParserDetail.htmlParser()
myparser.feed(data)

print json.dumps(myparser.data)