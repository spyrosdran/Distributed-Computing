FROM python:3
ADD client.py
ADD client_protocol.py
RUN pip install zeep
CMD ["python", "./client.py"]