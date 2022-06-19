from db import Meme


def like_meme(meme: Meme):
    if not meme.new_like:
        meme.likes += 1
        meme.new_like = True


print('>>> Начнем <<<')
print('>>> Чтобы лайкнуть надо написать в консоль: like. Для скипа - skip. Для завершения игры - exit')
for meme in Meme.select():
    print(f'Мем: {meme.url}')
    while (command := input().lower()) != 'skip':
        if command == 'like':
            like_meme(meme)
            print('>>> Вы лайкнули мем')
            print(f'>>> Текущее количество лайков у мема: {meme.likes}')
        elif command == 'exit':
            print('>>> До свидания <<<')
            exit()
        else:
            print('>>> Неясная команда')
print('>>> До свидания <<<')
