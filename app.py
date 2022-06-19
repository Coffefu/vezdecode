import numpy.random as npr
from fastapi import FastAPI
from db import Meme

app = FastAPI()


@app.get("/memes_info")
async def memes_info():
    for meme in Meme.select():
        print(f'Автор: {meme.author.first_name} {meme.author.last_name}. Количество лайков: {meme.likes}')
    return 'Ok'


@app.get("/random_meme")
async def get_random_meme():
    """Выдает 1й мем с вероятностью в 0.3, а остальные с вероятностью 0.7 / (count_memes + meme.likes)"""
    count_memes = len(Meme.select())
    memes = [{
        "id": meme.id,
        "prod": 0.7 / (count_memes + meme.likes)
    } for meme in Meme.select()]
    memes[0]['prod'] = 0.3

    max_ = sum([meme['prod'] for meme in memes])
    selection_probs = [meme['prod'] / max_ for meme in memes]
    selected_meme = memes[npr.choice(count_memes, p=selection_probs)]
    return Meme.get_by_id(selected_meme['id']).data()
