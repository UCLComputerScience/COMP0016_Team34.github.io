import uuid

class Caller():

    def __init__(self,name,dob):
        self.name = name
        self.dob = dob
        self.description = ''
        self.id = uuid.uuid4().hex #generate randomID
        self.changes = {"name":self.name,"dob":self.dob,"id":self.id}
        self.active = True
    
    def to_JSON(self):
        return self.changes
    
    def add_description(self,desc):
        self.description = desc
        self.changes["description"] = self.description
    
    def clear_changes(self):
        self.changes = {"id":self.id}
    
    def remove(self):
        self.active = False
        self.changes["active"] = self.active
    
    def get_All(self):
        return self.__dict__