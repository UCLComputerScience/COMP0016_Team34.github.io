class Caller():

    def __init__(self,name,dob):
        self.name = name
        self.dob = dob
        self.description = ''
    
    def to_JSON(self):
        return self.__dict__
    
    def add_description(self,desc):
        self.description = desc