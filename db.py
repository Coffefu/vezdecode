from peewee import Model, SqliteDatabase, DateTimeField, IntegerField, BooleanField, CharField, ForeignKeyField

db = SqliteDatabase('memes_base', pragmas={
    'foreign_keys': 1,
    'cache_size': -1 * 64000,  # 64MB
})


def get_data(inst):
    if isinstance(inst, BaseModel):
        return inst.data()
    return inst


class BaseModel(Model):
    def data(self, **kwargs) -> dict:
        """Returns a dictionary with fields, excluding hidden_fields"""
        fields = set(self.__data__.keys()).union(set(kwargs.pop('fields', '')))
        fields -= set(kwargs.pop('hide', ''))

        if fields is None:
            return self.__data__
        return {k: get_data(getattr(self, k)) for k in self.__data__.keys() if k in fields}

    class Meta:
        database = db
        order_by = 'id'


class User(BaseModel):
    vk_id = IntegerField()
    first_name = CharField(max_length=200)
    last_name = CharField(max_length=200)


class Meme(BaseModel):
    meme_id = IntegerField()
    date = DateTimeField()
    likes = IntegerField()
    new_like = BooleanField()
    author = ForeignKeyField(User)
    url = CharField(max_length=500)


db.create_tables([User, Meme])
