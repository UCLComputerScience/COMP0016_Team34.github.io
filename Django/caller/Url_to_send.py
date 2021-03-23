
class Url_to_send():

    def __init__(self,url,desc,text):
        self.url = url
        self.description = desc
        self.text = text

    
    def get_url(self):
        return self.url

    def get_description(self):
        return self.description

    def get_text(self):
        return self.text
