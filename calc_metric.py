import cv2
from sklearn.cluster import KMeans
from collections import Counter
import numpy as np


def calc_metric(image, x, y, w, h):
    image = image[y:y + h, x:x + w]
    hsv_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    hsv_image = hsv_image.reshape((hsv_image.shape[0] * hsv_image.shape[1], 3))
    clt = KMeans(n_clusters=4)
    labels = clt.fit_predict(hsv_image)
    label_counts = Counter(labels)
    dominant_color = clt.cluster_centers_[label_counts.most_common(1)[0][0]]
    dom_color = list(dominant_color)

    dom_color_hsv = np.full((image.shape[0], 100, 3), dom_color, dtype='uint8')
    dom_color_bgr = cv2.cvtColor(dom_color_hsv, cv2.COLOR_HSV2BGR)
    output_image = np.hstack((image, dom_color_bgr))
    cv2.imshow('Dominant Color', output_image)
    cv2.waitKey(0)

    return dom_color
