import vk_api

from db import User, Meme

login, password = 'LOGIN', 'PASSWORD'
vk_session = vk_api.VkApi(login, password)
vk_session.auth()
vk = vk_session.get_api()


def load_memes():
    # data = vk.photos.get(owner_id=-197700721, album_id=283939598, count=100, extended=1, rev=0)
    data = vk.photos.get(owner_id=-175284023, album_id='wall', count=200, extended=1, rev=0)
    for photo in data['items']:
        if photo['user_id'] != 100:
            author_data = vk.users.get(user_ids=photo['user_id'])[0]
            author = User.get_or_create(
                vk_id=author_data['id'],
                first_name=author_data['first_name'],
                last_name=author_data['last_name']
            )[0]
        else:
            group_data = vk.groups.getById(group_id=-1 * photo['owner_id'])[0]
            author = User.get_or_create(
                vk_id=group_data['id'],
                first_name=group_data['name'],
                last_name=''
            )[0]

        photo.update({
            'photo_url': photo.pop('sizes')[-1]['url']
        })
        if not Meme.get_or_none(Meme.meme_id == photo['id']):
            Meme.create(
                meme_id=photo['id'],
                date=photo['date'],
                likes=photo['likes']['count'],
                new_like=False,
                author=author,
                url=photo['photo_url'],
            )


if __name__ == '__main__':
    load_memes()
