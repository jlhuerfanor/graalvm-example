sampleBuffer = []

def average(sampleBuffer, index):
    samplesOfIndex = [ s[index] for s in sampleBuffer ]
    return sum(samplesOfIndex) / len(samplesOfIndex)

def filter_sample(sample):
    if len(sampleBuffer) > 5:
        sampleBuffer.pop(0)
    sampleBuffer.append(sample)
    return [ average(sampleBuffer, i) for i in range(len(sample)) ]


