class Caller():

    def __init__(self,name,dob):
        self.name = name
        self.dob = dob
    
    def to_JSON(self):
        return self.__dict__