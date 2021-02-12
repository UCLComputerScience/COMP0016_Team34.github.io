import time


class Caller():

    def __init__(self,name,dob, caller_id):
        self.name = name
        self.dob = dob
        self.description = ''
        self.id = caller_id #generate randomID
        self.changes = {"name":self.name,"dob":self.dob,"id":self.id}
        self.active = True
        self.last_active = time.time()
        self.TIME_TO_DIE = 10 #caller will die if last active time was 600 seconds ago (10 mins)

    
    def to_JSON(self):
        if(time.time() - self.last_active >= self.TIME_TO_DIE):
            self.clear_changes()
            self.remove()
        return self.changes
    
    def add_description(self,desc):
        self.description = desc
        self.changes["description"] = self.description
    
    def clear_changes(self):
        self.changes = {"id":self.id}
    
    def remove(self):
        self.active = False
        self.changes["active"] = self.active

    def update_time(self):
        self.last_active = time.time()
        print(self.last_active)
    
    def get_All(self):
        return self.__dict__
    
