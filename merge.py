import cv2
import csv


def merge_channels(input_dir, output_dir):
    files = {}
    imcount = int(open(f'{input_dir}/image_counter.txt', 'r').read())

    with open(f'{input_dir}/description.csv', newline='') as file:
        data = csv.DictReader(file, delimiter=',')

        for row in data:
            if row['full_image_index'] not in files:
                files[row['full_image_index']] = [row['image_path']]
            else:
                files[row['full_image_index']].append(row['image_path'])

    counter = 0
    for k, v in files.items():
        counter += 1
        b, g, r = (
            cv2.imread(f'{input_dir}/data/{v[0]}'),
            cv2.imread(f'{input_dir}/data/{v[1]}'),
            cv2.imread(f'{input_dir}/data/{v[2]}'),
        )

        img = cv2.merge((b[:, :, 0], g[:, :, 0], r[:, :, 0]))
        cv2.imwrite(f'{output_dir}/{k}.jpg', img)

        if counter >= imcount:
            break
